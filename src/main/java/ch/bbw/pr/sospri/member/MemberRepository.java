package ch.bbw.pr.sospri.member;

import org.springframework.data.repository.CrudRepository;
/**
 * @author marc.welz
 * @version 31.05.2022
 */
                                                       //Klasse, id-Typ
public interface MemberRepository extends CrudRepository<Member, Long>{
	//Da wir eine embedded database verwenden, braucht es keine Conecction Information.
}

