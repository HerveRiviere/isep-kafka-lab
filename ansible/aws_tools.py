import boto3

REGION = 'eu-west-3'

def getInstances():
	ec2 = boto3.client('ec2')
	return ec2.describe_instances(Filters=[{"Name" :"tag:name", "Values":["isep-kafka"] }])

def printInstancesState() :
	reservations = getInstances()
	for reservation in reservations['Reservations'] :
		for instance in reservation['Instances'] :
			ip_adress = instance['PublicIpAddress']
			if(ip_adress!=None):
				print (ip_adress+"\t"+instance['State']['Name'])


def generateInventory() :
	reservations = getInstances()
	print ("[isep-kafka]")
	for reservation in reservations['Reservations'] :
		for instance in reservation['Instances'] :
			ip_adress = instance['PublicIpAddress']
			if(ip_adress!=None):
				print (ip_adress+"  ansible_user=ec2-user")

def generateListingGDoc() :
	reservations = getInstances()
	for reservation in reservations['Reservations'] :
		for instance in reservation['Instances'] :
			ip_adress = instance['PublicIpAddress']
			if(ip_adress!=None):
				print (ip_adress+"/8080/guacamole")
