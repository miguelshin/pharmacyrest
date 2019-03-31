#!groovy

node {
   // ------------------------------------
   // -- ETAPA: Compilar
   // ------------------------------------
   stage 'Compilar'
   echo 'Configurando variables'
   def mvnHome = tool 'M3'
   env.PATH = "${mvnHome}\bin:${env.PATH}"
   echo "var mvnHome='${mvnHome}'"
   echo "var env.PATH='${env.PATH}'"
   // -- Descarga código desde SCM
   echo 'Descargando código de SCM'
   checkout scm
   // -- Compilando
   echo 'Tests'
   bat "${mvnHome}\\\\bin\\\\mvn test"

   stage 'Tests'
   // -- Compilando
   echo 'Compilando aplicación'
   bat "${mvnHome}\\\\bin\\\\mvn clean install -Dmaven.test.skip"

}
