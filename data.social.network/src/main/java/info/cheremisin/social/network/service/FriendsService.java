package info.cheremisin.social.network.service;

import info.cheremisin.social.network.dto.UserDTO;

import java.util.Map;
import java.util.Set;

public interface FriendsService {

    Map<String, Set<UserDTO>> getFriends(Long userId, String search);

    Set<UserDTO> getAcceptedFriendshipUsers(Long id);

    void deleteFriendship(UserDTO userDTO, Long friendId);

    void acceptFriendship(UserDTO userDTO, Long friendId);

    void addToFriends(UserDTO user, Long friendId);

    Boolean checkFriendship(UserDTO user, UserDTO friend);
}
