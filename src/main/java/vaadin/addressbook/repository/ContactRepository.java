package vaadin.addressbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vaadin.addressbook.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, String> {
}
