package info.cheremisin.social.network.service.impl;

import info.cheremisin.social.network.converters.UserDtoToUserConverter;
import info.cheremisin.social.network.converters.UserToUserDtoConverter;
import info.cheremisin.social.network.dto.UserDTO;
import info.cheremisin.social.network.entities.Friendship;
import info.cheremisin.social.network.entities.User;
import info.cheremisin.social.network.repositories.FriendshipRepository;
import info.cheremisin.social.network.repositories.UserRepository;
import info.cheremisin.social.network.service.FriendsService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class FriendsServiceImpl implements FriendsService {
    private FriendshipRepository friendshipRepository;
    private UserToUserDtoConverter userToUserDtoConverter;
    private UserDtoToUserConverter userDtoToUserConverter;
    private UserRepository userRepository;

    public FriendsServiceImpl(FriendshipRepository friendshipRepository, UserToUserDtoConverter userToUserDtoConverter,
                              UserDtoToUserConverter userDtoToUserConverter, UserRepository userRepository) {
        this.friendshipRepository = friendshipRepository;
        this.userToUserDtoConverter = userToUserDtoConverter;
        this.userDtoToUserConverter = userDtoToUserConverter;
        this.userRepository = userRepository;
    }

    @Override
    public Map<String, Set<UserDTO>> getFriends(Long userId, String search) {
        List<Friendship> requests = friendshipRepository.findAllByUserSenderIdOrUserReceiverId(userId, userId);
        Map<Boolean, List<Friendship>> requestMap = requests.stream()
                .collect(Collectors.partitioningBy(Friendship::getAccepted));

        Predicate<User> userPredicate = u -> StringUtils.isEmpty(search) ||
                (u.getFirstName() + " " + u.getLastName()).toLowerCase().contains(search.toLowerCase());
        Set<UserDTO> usersNotAcceptedRequests = requestMap.get(false).stream()
                .filter(r -> r.getUserSender().getId().equals(userId))
                .map(Friendship::getUserReceiver)
                .filter(userPredicate)
                .map(u -> userToUserDtoConverter.convert(u))
                .collect(Collectors.toSet());

        Set<UserDTO> notAcceptedRequestsToUser = requestMap.get(false).stream()
                .filter(r -> r.getUserReceiver().getId().equals(userId))
                .map(Friendship::getUserSender)
                .filter(userPredicate)
                .map(u -> userToUserDtoConverter.convert(u))
                .collect(Collectors.toSet());

        Set<UserDTO> friendsOfUser = requestMap.get(true).stream()
                .map(r -> r.getUserSender().getId().equals(userId) ? r.getUserReceiver() : r.getUserSender())
                .filter(userPredicate)
                .map(u -> userToUserDtoConverter.convert(u))
                .collect(Collectors.toSet());

        Map<String, Set<UserDTO>> map = new HashMap<>();
        map.put("usersNotAcceptedRequests", usersNotAcceptedRequests);
        map.put("notAcceptedRequestsToUser", notAcceptedRequestsToUser);
        map.put("friendsOfUser", friendsOfUser);

        return map;
    }

    @Override
    @Transactional
    public void deleteFriendship(UserDTO userDTO, Long friendId) {
        User user = userDtoToUserConverter.convert(userDTO);
        User friend = userRepository.findById(friendId)
                .orElseThrow(() -> new RuntimeException("User not found with id = " + friendId));
        friendshipRepository.deleteFriendRequests(user, friend);
    }

    @Override
    @Transactional
    public void acceptFriendship(UserDTO userDTO, Long friendId) {
        User user = userDtoToUserConverter.convert(userDTO);
        User friend = userRepository.findById(friendId)
                .orElseThrow(() -> new RuntimeException("User not found with id = " + friendId));
        friendshipRepository.deleteFriendRequests(user, friend);
        friendshipRepository.addFriendship(user, friend);
    }

    @Override
    @Transactional
    public void addToFriends(UserDTO userDTO, Long friendId) {
        User user = userDtoToUserConverter.convert(userDTO);
        User friend = userRepository.findById(friendId)
                .orElseThrow(() -> new RuntimeException("User not found with id = " + friendId));
        friendshipRepository.addToFriends(user, friend);
    }

    @Override
    @Transactional
    public Boolean checkFriendship(UserDTO userDTO, UserDTO friendDTO) {
        User user = userDtoToUserConverter.convert(userDTO);
        User friend = userDtoToUserConverter.convert(friendDTO);
        return friendshipRepository.checkFriendshipExists(user, friend);
    }
}
