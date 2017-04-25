FROM java:8
ADD ./ /usr/src
WORKDIR /usr/src
RUN javac Server/ServerKnot.java
RUN javac Server/ServerThread.java
RUN javac Server/Player.java
RUN javac Shared/Game.java
RUN javac Shared/Question.java
ENTRYPOINT java Server/ServerKnot
EXPOSE 8081
