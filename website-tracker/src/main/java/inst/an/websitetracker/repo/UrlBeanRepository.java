package inst.an.websitetracker.repo;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import inst.an.websitetracker.UrlBean;


public interface UrlBeanRepository extends JpaRepository<UrlBean, Long>{
	//@Query(value="SELECT * FROM TWEET where tweeter_id in (SELECT u.id FROM user_profile u LEFT JOIN connection c WHERE c.followed_by_id = u.id AND c.follower_id = :userId)", nativeQuery=true)
	@Query(value="select * from (SELECT * FROM url_bean order by hits desc) where rownum() < 4", nativeQuery=true)
	Collection<UrlBean> findTop3Visited();

	UrlBean findByDomain(String domain);
}
