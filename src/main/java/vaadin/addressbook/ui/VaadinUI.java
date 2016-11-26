package vaadin.addressbook.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.GeneratedPropertyContainer;
import com.vaadin.data.util.PropertyValueGenerator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.ButtonRenderer;
import com.vaadin.ui.renderers.ClickableRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import vaadin.addressbook.model.Contact;
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

    private final ContactService contactService;

    @Autowired
    public VaadinUI(ContactService contactService) {
        this.contactService = contactService;
    }

    @Override
    protected void init(VaadinRequest request) {
        // build grid
        Grid grid = new Grid(getGeneratedPropertyContainer());
        grid.setSizeFull();
        grid.setColumns("name", "phone", "email", "delete");
        grid.getColumn("delete")
                .setRenderer(new ButtonRenderer((ClickableRenderer.RendererClickListener) event -> {
                    Contact contact = (Contact) event.getItemId();
                    contactService.delete(contact.getId());
                    grid.getContainerDataSource().removeItem(event.getItemId());
                }));

        // build layout
        VerticalLayout mainLayout = new VerticalLayout(grid);
        setContent(mainLayout);

        // Configure layouts and components
        mainLayout.setMargin(true);
        mainLayout.setSpacing(true);
    }

    private GeneratedPropertyContainer getGeneratedPropertyContainer() {
        BeanItemContainer<Contact> container = new BeanItemContainer<>(Contact.class, contactService.findAll());

        GeneratedPropertyContainer gpc = new GeneratedPropertyContainer(container);
        gpc.addGeneratedProperty("delete",
                new PropertyValueGenerator<String>() {

                    @Override
                    public String getValue(Item item, Object itemId,
                                           Object propertyId) {
                        return "Delete";
                    }

                    @Override
                    public Class<String> getType() {
                        return String.class;
                    }
                });
        return gpc;
    }

}

