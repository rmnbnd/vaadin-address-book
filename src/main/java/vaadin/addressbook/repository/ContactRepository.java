package vaadin.addressbook.repository;

import org.springframework.data.repository.CrudRepository;
import vaadin.addressbook.model.Contact;

public interface ContactRepository extends CrudRepository<Contact, Long> {
}
