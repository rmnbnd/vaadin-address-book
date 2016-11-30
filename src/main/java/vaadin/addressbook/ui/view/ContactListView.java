package vaadin.addressbook.ui.view;

import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.GeneratedPropertyContainer;
import com.vaadin.data.util.PropertyValueGenerator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.ButtonRenderer;
import com.vaadin.ui.renderers.ClickableRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import vaadin.addressbook.model.Contact;
import vaadin.addressbook.service.ContactService;

import javax.annotation.PostConstruct;

@SpringView(name = ContactListView.VIEW_NAME)
public class ContactListView extends VerticalLayout implements View {

    static final String VIEW_NAME = "";

    private final ContactService contactService;

    @Autowired
    public ContactListView(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostConstruct
    void init() {
        Grid grid = new Grid(getGeneratedPropertyContainer());
        grid.setSizeFull();
        grid.setColumns("name", "phone", "email", "delete", "edit");
        grid.getColumn("delete")
                .setRenderer(new ButtonRenderer((ClickableRenderer.RendererClickListener) event -> {
                    Contact contact = (Contact) event.getItemId();
                    contactService.delete(contact.getId());
                    grid.getContainerDataSource().removeItem(event.getItemId());
                }));
        grid.getColumn("edit")
                .setRenderer(new ButtonRenderer((ClickableRenderer.RendererClickListener) event -> {
                    Contact contact = (Contact) event.getItemId();
                    getUI().getNavigator().navigateTo(ContactFormView.VIEW_NAME + "/" + contact.getId());
                }));


        // build layout
        addComponent(grid);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    private GeneratedPropertyContainer getGeneratedPropertyContainer() {
        BeanItemContainer<Contact> container = new BeanItemContainer<>(Contact.class, contactService.findAll());

        GeneratedPropertyContainer gpc = new GeneratedPropertyContainer(container);
        gpc.addGeneratedProperty("delete",
                new PropertyValueGenerator<String>() {

                    @Override
                    public String getValue(Item item, Object itemId, Object propertyId) {
                        return "Delete";
                    }

                    @Override
                    public Class<String> getType() {
                        return String.class;
                    }
                });
        gpc.addGeneratedProperty("edit",
                new PropertyValueGenerator<String>() {

                    @Override
                    public String getValue(Item item, Object itemId, Object propertyId) {
                        return "Edit";
                    }

                    @Override
                    public Class<String> getType() {
                        return String.class;
                    }
                });
        return gpc;
    }
}