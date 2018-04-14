package io.spring2go.lab.socialgithub.config;

import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.github.api.GitHub;
import org.springframework.social.github.connect.GitHubConnectionFactory;

@Configuration
@EnableSocial
@EnableConfigurationProperties(GitHubProperties.class)
public class GitHubConfiguration extends SocialAutoConfigurerAdapter {

	private final GitHubProperties properties;

	public GitHubConfiguration(GitHubProperties properties) {
		this.properties = properties;
	}

	@Bean
	@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
	public GitHub gitHub(ConnectionRepository repository) {
		Connection<GitHub> connection = repository
				.findPrimaryConnection(GitHub.class);
		return connection != null ? connection.getApi() : null;
	}

	@Bean
	public ConnectController connectController(
			ConnectionFactoryLocator factoryLocator,
			ConnectionRepository repository) {

		ConnectController controller = new ConnectController(
			factoryLocator, repository);
		controller.setApplicationUrl("http://localhost:8080");
		return controller;
	}

	@Override
	protected ConnectionFactory<?> createConnectionFactory() {
		return new GitHubConnectionFactory(properties.getAppId(),
				properties.getAppSecret());
	}
}
