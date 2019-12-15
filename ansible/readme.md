## Prerquisite
Ansible 2.X (`pip install ansible`)
boto3 (python aws cli) (`pip install boto3`)

#Amazon API credential

$ vi ~/.boto
```
[Credentials]
aws_access_key_id = XXXXXXXXXXXXXXXXXXXXXXXX
aws_secret_access_key = XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
```
$ vi ~/.aws/config
```
[default]
region=eu-west-3
```

##Amazon pem file
chmod 400 *.pem
connect to ssh : ssh -l ec2-user -i <path_to_pem_file> ip

##Ansible : don't ask ssh fingerprint
export ANSIBLE_HOST_KEY_CHECKING=False
cd Isep-kafka/ansible

## Check connection :
python -c 'import aws_tools as aws;aws.printInstancesState()'


## Generate inventory :
python -c 'import aws_tools as aws;aws.generateInventory()'> inventory/inventory

## Check inventory :
ansible all -i inventory/inventory --private-key <path_to_pem_file> -m ping

##launch guacamole install
ansible-playbook -i inventory/inventory --private-key <path_to_pem_file> install_guacamole.yml
