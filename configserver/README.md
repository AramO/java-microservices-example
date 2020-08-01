---------------------------------------------------------------------------------------
                                        The Config Server.
---------------------------------------------------------------------------------------

---------------------------------------------------------------------------------------
                                Managing configuration.
---------------------------------------------------------------------------------------

1.  Segregate—We want to completely separate the services configuration informa-
        tion from the actual physical deployment of a service. Application configura-
        tion shouldn’t be deployed with the service instance. Instead, configuration
        information should either be passed to the starting service as environment vari-
        ables or read from a centralized repository when the service starts.

2.  Abstract—Abstract the access of the configuration data behind a service interface.
        Rather than writing code that directly accesses the service repository (that is, 
        read the data out of a file or a database using JDBC ), have the application use
        a REST -based JSON service to retrieve the configuration data.

3.  Centralize—Because a cloud-based application might literally have hundreds of
        services, it’s critical to minimize the number of different repositories used to
        hold configuration information. Centralize your application configuration into
        as few repositories as possible.

4.  Harden—Because your application configuration information is going to be
        completely segregated from your deployed service and centralized, it’s critical
        that whatever solution you utilize can be implemented to be highly available
        and redundant.

---------------------------------------------------------------------------------------
                                Configuration management architecture.
---------------------------------------------------------------------------------------

1.  When a microservice instance comes up, it’s going to call a service endpoint to
        read its configuration information that’s specific to the environment it’s operat-
        ing in. The connection information for the configuration management (con-
        nection credentials, service endpoint, and so on) will be passed into the
        microservice when it starts up.

2.  The actual configuration will reside in a repository. Based on the implementation
        of your configuration repository, you can choose to use different implementa-
        tions to hold your configuration data. The implementation choices can include
        files under source control, a relational database, or a key-value data store.

3.  The actual management of the application configuration data occurs indepen-
        dently of how the application is deployed. Changes to configuration manage-
        ment are typically handled through the build and deployment pipeline where
        changes of the configuration can be tagged with version information and
        deployed through the different environments.

4.  When a configuration management change is made, the services that use that
        application configuration data must be notified of the change and refresh their
        copy of the application data.

---------------------------------------------------------------------------------------
                                    Summary
---------------------------------------------------------------------------------------

1.  Spring Cloud configuration server allows you to set up application properties with environment specific values.
2.  Spring uses Spring profiles to launch a service to determine what environment properties are to be retrieved from the Spring Cloud Config service.
3.  Client-side load balancing can provide an extra level of performance and resiliency vice call.
4.  Spring Cloud configuration service can use a file-based or Git-based application configuration repository to store application properties.
5.  Spring Cloud configuration service allows you to encrypt sensitive property files using symmetric and asymmetric encryption.