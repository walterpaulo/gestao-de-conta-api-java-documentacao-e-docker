
#!/bin/bash
#####	NOME:				stop.sh
#####	VERSÃO:				1.0.0
#####	DESCRIÇÃO:			Parar aplicacao.
#####	DATA DA CRIAÇÃO:	20/01/2023
#####	ESCRITO POR:		Walter Paulo
#####	E-MAIL:				walter0paulo@hotmail.com
#####	DISTRO:				Debian GNU/Linux
#####	LICENÇA:			GPLv3
#####	REPOSITÓRIO:        https://github.com/walterpaulo
#####	PORTFOLIO:          https://www.walterpaulo.com.br

IMAGE=$(docker images |grep "walterpaulo/java17tomcat"|awk '{print $3}')
NOME_CONTAINER=$(docker ps |grep "javawar"|awk '{print $13}')
if [ $NOME_CONTAINER ]; then
    docker rm -f $NOME_CONTAINER
    echo "Excluded Container"
else
   echo "Container not found"
fi 

if [ $IMAGE ]; then
    docker rmi $IMAGE
   echo "Excluded Image"
else
   echo "Image not found"
fi 
# echo "Limpando cache app"
# mvn clean