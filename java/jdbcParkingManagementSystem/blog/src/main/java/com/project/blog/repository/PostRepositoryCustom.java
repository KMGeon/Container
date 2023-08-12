package com.project.blog.repository;

import com.project.blog.domain.Post;
import com.project.blog.dto.response.PostSearch;

import java.util.List;

public interface PostRepositoryCustom {

    List<Post> getList(PostSearch postSearch);
}
