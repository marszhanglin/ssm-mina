### 解决导了太多jar包导致(自动装载的时候还是会这样，没卵用) ###

ava.lang.OutOfMemoryError: **PermGen space**

JAVA_OPTS="-server -XX:PermSize=64M -XX:MaxPermSize=128m


