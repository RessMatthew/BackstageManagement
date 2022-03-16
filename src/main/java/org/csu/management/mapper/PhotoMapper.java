package org.csu.management.mapper;


import org.csu.management.domain.Photo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoMapper {
    List<Photo> getAllPhotoDescn();
}
