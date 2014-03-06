QCM en play framework

Installation speciale pour ENI :

Télécharger le dernier JDK java (pour ce projet 1.7)

Ajouter dans le path :
C:\Program Files\Java\jdk1.7.0_51\bin
D:\play-2.2.1
et
HTTP_PROXY=http://proxy.stagiaires.local:8080

Ensuite avec power shell :
$ cd path/to/myapp
$ play run


Avec cygwin : // Fonctionne tres mal
$ cd path/to/myapp
$ play.bat run


Il faut aussi configuerer Mysql.
Sqlite ne fonctionne pas avec Hibernate (trop compliqué à mettre en place)

Il faut créer la table "play_run_dev"
$ mysql -u root
$ create database play_run_dev

Pour que Hibernate créé automatiquement les tables+ contraintes,
allé dans
conf\META-INF\persistence.xml
et modifier
			<property name="hibernate.hbm2ddl.auto" value="update"/>
par
			<property name="hibernate.hbm2ddl.auto" value="create"/>

Avec cygwin pour lancer mysql
mysqld_save
et le classique
mysql -u root

Si hibernate fail à détruire les table :
$mysql
 drop database play_run_dev;
ou
 mysql -u root  -e "drop database play_run_dev;"


 create database play_run_dev;
ou
 mysql -u root  -e "create database play_run_dev;"
