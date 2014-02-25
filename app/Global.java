import play.Application;
import play.Logger;
import play.GlobalSettings;
import play.api.mvc.EssentialFilter;
import play.filters.csrf.CSRFFilter;

public class Global extends GlobalSettings {
  @Override
  public <T extends EssentialFilter> Class<T>[] filters() {
    return new Class[]{CSRFFilter.class};
  }

}
