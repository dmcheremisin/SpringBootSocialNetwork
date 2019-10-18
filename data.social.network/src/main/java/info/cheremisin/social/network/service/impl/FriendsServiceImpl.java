package info.cheremisin.social.network.service.impl;

import info.cheremisin.social.network.converters.UserToUserDtoConverter;
import info.cheremisin.social.network.dto.UserDTO;
import info.cheremisin.social.network.entities.Friendship;
import info.cheremisin.social.network.repositories.FriendshipRepository;
import info.cheremisin.social.network.service.FriendsService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FriendsServiceImpl implements FriendsService {
    private FriendshipRepository friendshipRepository;
    private UserToUserDtoConverter converter;

    public FriendsServiceImpl(FriendshipRepository friendshipRepository, UserToUserDtoConverter converter) {
        this.friendshipRepository = friendshipRepository;
        this.converter = converter;
    }

    @Override
    public Map<String, Set<UserDTO>> getFriends(Long userId) {
        List<Friendship> requests = friendshipRepository.findAllByUserSenderIdOrUserReceiverId(userId, userId);
        Map<Boolean, List<Friendship>> requestMap = requests.stream()
                .collect(Collectors.partitioningBy(Friendship::getAccepted));

        Set<UserDTO> usersNotAcceptedRequests = requestMap.get(false).stream()
                .filter(r -> r.getUserSender().getId().equals(userId))
                .map(Friendship::getUserReceiver)
                .map(u -> converter.convert(u))
                .collect(Collectors.toSet());

        Set<UserDTO> notAcceptedRequestsToUser = requestMap.get(false).stream()
                .filter(r -> r.getUserReceiver().getId().equals(userId))
                .map(Friendship::getUserSender)
                .map(u -> converter.convert(u))
                .collect(Collectors.toSet());

        Set<UserDTO> friendsOfUser = requestMap.get(true).stream()
                .map(r -> r.getUserSender().getId().equals(userId) ? r.getUserReceiver() : r.getUserSender())
                .map(u -> converter.convert(u))
                .collect(Collectors.toSet());

        Map<String, Set<UserDTO>> map = new HashMap<>();
        map.put("usersNotAcceptedRequests", usersNotAcceptedRequests);
        map.put("notAcceptedRequestsToUser", notAcceptedRequestsToUser);
        map.put("friendsOfUser", friendsOfUser);

        return map;
    }
}
