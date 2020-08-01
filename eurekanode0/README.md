---------------------------------------------------------------------------------------
                                        The Eureka Node.
---------------------------------------------------------------------------------------

---------------------------------------------------------------------------------------
The solution for a cloud-based microservice environment is to use a service-discovery mechanism.
---------------------------------------------------------------------------------------

1.  Highly available—Service discovery needs to be able to support a “hot” clustering environment
        where service lookups can be shared across multiple nodes in
        a service discovery cluster. If a node becomes unavailable, other nodes in the
        cluster should be able to take over.

2.  Peer-to-peer—Each node in the service discovery cluster shares the state of a service instance.

3.  Load balanced—Service discovery needs to dynamically load balance requests
        across all service instances to ensure that the service invocations are spread
        across all the service instances managed by it. In many ways, service discovery
        replaces the more static, manually managed load balancers used in many early
        web application implementations.

4.  Resilient—The service discovery’s client should “cache” service information
        locally. Local caching allows for gradual degradation of the service discovery
        feature so that if service discovery service does become unavailable, applica-
        tions can still function and locate the services based on the information main-
        tained in its local cache.

5.  Fault-tolerant—Service discovery needs to detect when a service instance isn’t
        healthy and remove the instance from the list of available services that can take
        client requests. It should detect these faults with services and take action with-
        out human intervention.

---------------------------------------------------------------------------------------
                                client-side load balancing.
---------------------------------------------------------------------------------------

1.  It will contact the service discovery service for all the service instances a service
        consumer is asking for and then cache data locally on the service consumer’s
        machine.

2.  Each time a client wants to call the service, the service consumer will look up
        the location information for the service from the cache. Usually client-side
        caching will use a simple load balancing algorithm like the “round-robin” load
        balancing algorithm to ensure that service calls are spread across multiple ser-
        vice instances.

3.  The client will then periodically contact the service discovery service and
        refresh its cache of service instances. The client cache is eventually consistent,
        but there’s always a risk that between when the client contacts the service dis-
        covery instance for a refresh and calls are made, calls might be directed to a ser-
        vice instance that isn’t healthy.


---------------------------------------------------------------------------------------
For instance, to see the organization service in the registry you can call http://localhost:8761/eureka/apps/organizationservice .
---------------------------------------------------------------------------------------

---------------------------------------------------------------------------------------
                                    Summary
---------------------------------------------------------------------------------------

1.  The service discovery pattern is used to abstract away the physical location of services.
2.  A service discovery engine such as Eureka can seamlessly add and remove service instances from an environment without the service clients being impacted.
3.  Client-side load balancing can provide an extra level of performance and resiliency vice call.
4.  Eureka is a Netflix project that when used with Spring Cloud, is easy to set up and configure.
5.  You can use three different mechanisms in Spring Cloud, Netflix Eureka, and Nefflix Ribbon to invoke a service. These mechanisms included
        – Using a Spring Cloud service DiscoveryClient
        – Using Spring Cloud and Ribbon-backed RestTemplate
        – Using Spring Cloud and Netflix’s Feign client