package info.cheremisin.social.network.converters;

import info.cheremisin.social.network.dto.PageDTO;
import info.cheremisin.social.network.dto.UserDTO;
import info.cheremisin.social.network.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Dmitrii on 03.10.2019.
 */

@Component
@RequiredArgsConstructor
public class PageToPageDtoUserConverter implements Converter<Page<User>, PageDTO<UserDTO>> {

    private final UserToUserDtoConverter userToUserDtoConverter;

    @Override
    public PageDTO<UserDTO> convert(Page<User> page) {
        List<UserDTO> list = page.getContent().stream()
                .map(e -> userToUserDtoConverter.convert(e))
                .collect(Collectors.toList());

        return PageDTO.<UserDTO>builder()
                .content(list)
                .totalPages(page.getTotalPages())
                .currentPage(page.getNumber())
                .hasNext(page.hasNext())
                .hasPrevious(page.hasPrevious())
                .build();
    }

}
