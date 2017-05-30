### 解决导了太多jar包导致 ###

ava.lang.OutOfMemoryError: **PermGen space**

JAVA_OPTS="-server -XX:PermSize=64M -XX:MaxPermSize=128m


