package vaadin.addressbook.ui.view;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import vaadin.addressbook.model.Contact;
import vaadin.addressbook.service.ContactService;

@SpringView(name = ContactFormView.VIEW_NAME)
public class ContactFormView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "contact";

    private final ContactService contactService;

    private Contact contact;

    private TextField name = new TextField("Name");
    private TextField email = new TextField("Email");
    private TextField phone = new TextField("Phone");

    @Autowired
    public ContactFormView(ContactService contactService) {
        this.contactService = contactService;

        Button cancel = new Button("Cancel");
        Button save = new Button("Save", FontAwesome.SAVE);
        CssLayout actions = new CssLayout(save, cancel);

        // Aff components
        addComponents(name, email, phone, actions);

        // Configure and style components
        setSpacing(true);
        setMargin(true);
        actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        // Configure events
        save.addClickListener((Button.ClickListener) event -> {
            contactService.save(contact);
            getUI().getNavigator().navigateTo(ContactListView.VIEW_NAME);
        });
        cancel.addClickListener(event -> getUI().getNavigator().navigateTo(ContactListView.VIEW_NAME));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        if (StringUtils.isEmpty(event.getParameters())) {
            contact = new Contact();
        } else {
            contact = contactService.findById(event.getParameters());
        }
        BeanFieldGroup.bindFieldsUnbuffered(contact, this);
    }

}
