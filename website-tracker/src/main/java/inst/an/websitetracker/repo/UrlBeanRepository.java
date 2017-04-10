package inst.an.websitetracker.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import inst.an.websitetracker.beans.UrlBean;


public interface UrlBeanRepository extends JpaRepository<UrlBean, Long>{
	//@Query(value="SELECT * FROM TWEET where tweeter_id in (SELECT u.id FROM user_profile u LEFT JOIN connection c WHERE c.followed_by_id = u.id AND c.follower_id = :userId)", nativeQuery=true)
	@Query(value="select * from (SELECT domain, count(*) as hits FROM url_bean group by domain order by hits desc) where rownum() < 4", nativeQuery=true)
	List<Object[]> findTop3Visited();

	UrlBean findByDomain(String domain);
	
	@Query(value="SELECT domain, count(*) as hits FROM url_bean group by domain order by hits desc", nativeQuery=true)
	List<Object[]> getDomainsAndCount();
}
