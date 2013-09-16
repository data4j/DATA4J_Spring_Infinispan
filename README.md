Distributed Spring Beans via Infinispan

Infinispan is an extremely scalable, highly available key/value NoSQL datastore and distributed data grid platform. It is fully open source, written in Java and under JBoss umbrella.

This article shows how to distribute Spring beans by using Infinispan. In the following sample application, a new cluster called DATA4J has been created and a spring bean has been distributed by using a cache object called user-cache. It has been distributed between two members of the cluster.

Used Technologies : JDK 1.7.0_40, Spring 3.2.3, Infinispan 5.2.7, JGroups 3.2.7 and Maven 3.0.4