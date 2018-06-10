package si.um.feri.praktikum.facebook;

import java.io.Serializable;
import java.util.Map;
import java.util.Properties;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import lombok.Getter;
import lombok.Setter;
import org.brickred.socialauth.AuthProvider;
import org.brickred.socialauth.Profile;
import org.brickred.socialauth.SocialAuthConfig;
import org.brickred.socialauth.SocialAuthManager;
import org.brickred.socialauth.util.SocialAuthUtil;
import si.um.feri.praktikum.ejb.EJBOseba;

@ManagedBean
@SessionScoped
public class ProfileBean implements Serializable {

    private static final long serialVersionUID = 3658300628580536494L;

    @EJB
    private EJBOseba ejbOseba;

    private SocialAuthManager socialManager;
    @Getter
    @Setter
    private Profile profile;

    @SuppressWarnings("FieldCanBeLocal")
    private final String mainURL = "http://localhost:8080/FitnessClani_war_exploded/faces/index.xhtml";
    @SuppressWarnings("FieldCanBeLocal")
    private final String redirectURL = "http://localhost:8080/FitnessClani_war_exploded/faces/fbRedirect.xhtml";
    @SuppressWarnings("FieldCanBeLocal")
    private final String provider = "facebook";

    public void connectToFB() {
        Properties prop = System.getProperties();
        prop.put("graph.facebook.com.consumer_key", "2094342204111051");
        prop.put("graph.facebook.com.consumer_secret", "531053f54e085de4e208faf66535f111");
        prop.put("graph.facebook.com.custom_permissions", "public_profile,email");

        SocialAuthConfig socialConfig = SocialAuthConfig.getDefault();
        try {
            socialConfig.load(prop);
            socialManager = new SocialAuthManager();
            socialManager.setSocialAuthConfig(socialConfig);
            String URLReturn = socialManager.getAuthenticationUrl(provider, redirectURL);
            FacesContext.getCurrentInstance().getExternalContext().redirect(URLReturn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getUserProfile() throws Exception {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

        Map<String, String> parametersMap = SocialAuthUtil.getRequestParametersMap(request);

        if (socialManager != null) {
            AuthProvider provider = socialManager.connect(parametersMap);
            this.setProfile(provider.getUserProfile());
        }

        FacesContext.getCurrentInstance().getExternalContext().redirect(mainURL);
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index.xhtml?faces-redirect=true";
    }

    public boolean aliJeOsebaVclanjena(String email) {
        return ejbOseba.aliJeOsebaVclanjena(email);
    }

}
