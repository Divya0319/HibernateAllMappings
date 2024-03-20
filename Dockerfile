FROM openjdk:17
ADD target/hb-many-aws.jar hb-many-aws.jar
EXPOSE 8200
ENTRYPOINT [ "java","-jar","hb-many-aws.jar" ]