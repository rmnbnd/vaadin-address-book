package vaadin.addressbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vaadin.addressbook.model.Contact;
import vaadin.addressbook.repository.ContactRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<Contact> findAll() {
        return StreamSupport.stream(contactRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Contact findById(String id) {
        return contactRepository.findOne(Long.valueOf(id));
    }

    public Contact save(Contact contact) {
        return contactRepository.save(contact);
    }

    public void delete(Long id) {
        contactRepository.delete(id);
    }

}
