package br.com.caelum.vraptor.quartzjob;

import javax.servlet.ServletContext;

import br.com.caelum.vraptor.environment.Environment;
import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;

@Component
@ApplicationScoped
public class Env {

	private final Environment env;
	private final ServletContext context;

	public Env(Environment env, ServletContext context) {
		this.env = env;
		this.context = context;
	}

	public Env in(String name, Runnable toExecute) {
		if (env.getName().equals(name)) {
			toExecute.run();
		}
		return this;
	}

	public String host() {
		return env.get("host");
	}
	
	public String get(String key) {
		return env.get(key);
	}

	public String getHostAndContext() {
		return host() + context.getContextPath();
	}
}