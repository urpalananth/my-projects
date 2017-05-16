package inst.an.photoalbummanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import inst.an.photoalbummanager.beans.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByName(String name);

}
