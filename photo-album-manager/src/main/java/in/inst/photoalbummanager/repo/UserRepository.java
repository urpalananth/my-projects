package in.inst.photoalbummanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.inst.photoalbummanager.beans.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByName(String name);

}
