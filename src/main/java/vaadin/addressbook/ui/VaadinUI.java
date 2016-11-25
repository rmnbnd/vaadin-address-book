package vaadin.addressbook.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import vaadin.addressbook.model.Contact;
import vaadin.addressbook.repository.ContactRepository;
import vaadin.addressbook.service.ContactService;

/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@SpringUI
@Theme("mytheme")
public class VaadinUI extends UI {

    private Grid grid = new Grid();

    private final ContactService contactService;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    public VaadinUI(ContactService contactService) {
        this.contactService = contactService;
    }

    @Override
    protected void init(VaadinRequest request) {
        // build layout
        VerticalLayout mainLayout = new VerticalLayout(grid);
        setContent(mainLayout);

        // Configure layouts and components
        mainLayout.setMargin(true);
        mainLayout.setSpacing(true);

        grid.setSizeFull();
//        grid.setColumns("name", "phoneNumber", "email");

        // Initialize listing
        listContacts();
    }

    private void listContacts() {
        grid.setContainerDataSource(new BeanItemContainer(Contact.class, contactRepository.findAll()));
    }
}

