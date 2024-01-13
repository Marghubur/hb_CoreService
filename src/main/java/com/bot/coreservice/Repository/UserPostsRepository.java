package com.bot.coreservice.Repository;

import com.bot.coreservice.entity.UserPosts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPostsRepository extends JpaRepository<UserPosts, Long> {

    @Query(nativeQuery = true, value = "select up.* from userposts up order by up.UserPostId desc limit 1")
    UserPosts getLastUserPostRecord();

    @Query(nativeQuery = true, value = "select u.*, concat(e.FirstName, ' ', e.LastName) as FullName from userposts u inner join employee e on e.EmployeeId = u.PostedBy")
    List<UserPosts> getAllUserPosts();

    @Query(nativeQuery = true, value = "select up.* from userposts up where up.UserPostId = :userPostId")
    UserPosts getUserPostByUserPostId(@Param("userPostId") long userPostId);

}
