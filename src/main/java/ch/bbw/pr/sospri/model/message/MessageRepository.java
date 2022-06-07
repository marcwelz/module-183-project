package ch.bbw.pr.sospri.model.message;

import org.springframework.data.repository.CrudRepository;
/**
 * @author marc.welz
 * @version 31.05.2022
 */
                                                        //Klasse, id-Typ
public interface MessageRepository extends CrudRepository<Message, Long>{
	//Da wir eine embedded database verwenden, braucht es keine Conecction Information.
}

