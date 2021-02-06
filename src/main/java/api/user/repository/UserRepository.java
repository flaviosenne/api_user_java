package api.user.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import api.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{
    
}
