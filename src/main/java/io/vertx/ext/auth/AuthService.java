package io.vertx.ext.auth;

import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.ProxyIgnore;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.impl.AuthServiceImpl;
import io.vertx.serviceproxy.ProxyHelper;

import java.util.Set;

/**
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
@VertxGen
@ProxyGen
public interface AuthService {

  public static final String AUTH_REALM_CLASS_NAME_FIELD = "auth_realm_class_name";
  public static final String AUTH_REALM_TYPE_FIELD = "auth_realm_type";

  static AuthService create(Vertx vertx, JsonObject config) {
    return new AuthServiceImpl(vertx, config);
  }

  @GenIgnore
  static AuthService createWithRealm(Vertx vertx, AuthRealm authRealm, JsonObject config) {
    return new AuthServiceImpl(vertx, authRealm, config);
  }

  static AuthService createEventBusProxy(Vertx vertx, String address) {
    return ProxyHelper.createProxy(AuthService.class, vertx, address);
  }

  void login(JsonObject credentials, Handler<AsyncResult<Boolean>> resultHandler);

  void hasRole(String principal, String role, Handler<AsyncResult<Boolean>> resultHandler);

  void hasRoles(String principal, Set<String> roles, Handler<AsyncResult<Boolean>> resultHandler);

  void hasPermission(String principal, String permission, Handler<AsyncResult<Boolean>> resultHandler);

  void hasPermissions(String principal, Set<String> permissions, Handler<AsyncResult<Boolean>> resultHandler);

  @ProxyIgnore
  void start();

  @ProxyIgnore
  void stop();

}
