---------------------------------------------------------------------------------------
                                        The Zuul Gateway.
---------------------------------------------------------------------------------------

---------------------------------------------------------------------------------------
Examples of cross-cutting concerns that can be implemented in a service gateway include.
---------------------------------------------------------------------------------------

1.  Static routing—A service gateway places all service calls behind a single URL and
        API route. This simplifies development as developers only have to know about
        one service endpoint for all of their services.

2.  Dynamic routing—A service gateway can inspect incoming service requests and,
        based on data from the incoming request, perform intelligent routing based on
        who the service caller is. For instance, customers participating in a beta pro-
        gram might have all calls to a service routed to a specific cluster of services that
        are running a different version of code from what everyone else is using.

3.  Authentication and authorization—Because all service calls route through a service
        gateway, the service gateway is a natural place to check whether the caller of a ser-
        vice has authenticated themselves and is authorized to make the service call.

4.  Metric collection and logging—A service gateway can be used to collect metrics
        and log information as a service call passes through the service gateway. You can
        also use the service gateway to ensure that key pieces of information are in
        place on the user request to ensure logging is uniform. This doesn’t mean that
        shouldn’t you still collect metrics from within your individual services, but
        rather a services gateway allows you to centralize collection of many of your
        basic metrics, like the number of times the service is invoked and service
        response time.

---------------------------------------------------------------------------------------
                                    Summary
---------------------------------------------------------------------------------------

1.  Spring Cloud makes it trivial to build a services gateway.
2.  The Zuul services gateway integrates with Netflix’s Eureka server and can automatically map services registered with Eureka to a Zuul route.
3.  Client-side load balancing can provide an extra level of performance and resiliency vice call.
4.  Zuul can prefix all routes being managed, so you can easily prefix your routes with something like /api.
5.  Using Zuul, you can manually define route mappings. These route mappings are manually defined in the applications configuration files.
6.  By using Spring Cloud Config server, you can dynamically reload the route mappings without having to restart the Zuul server.
7.  You can customize Zuul’s Hystrix and Ribbon timeouts at global and individual service levels.
8.  Zuul allows you to implement custom business logic through Zuul filters. Zuul has three types of filters: pre-, post, and routing Zuul filters.
9.  Zuul pre-filters can be used to generate a correlation ID that can be injected into every service flowing through Zuul.
10. A Zuul post filter can inject a correlation ID into every HTTP service response back to a service client.
11. A custom Zuul route filter can perform dynamic routing based on a Eureka service ID to do A/B testing between different versions of the same service.