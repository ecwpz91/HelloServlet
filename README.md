# HelloServlet

## 1. Create project

```sh
oc new-project helloservlet --display-name="HelloServlet Demo"
```

## 2. Deploy binary artifact

```sh
oc import-image jboss-webserver-5/jws57-openjdk11-openshift-rhel8:latest --from=registry.redhat.io/jboss-webserver-5/jws57-openjdk11-openshift-rhel8:5.7.3-2.1687186259 --confirm
oc new-app --name=myapp jws57-openjdk11-openshift-rhel8:latest~https://github.com/ecwpz91/HelloServlet.git
```

## 3. Expose service

```sh
oc expose svc/myapp
```

## 4. Set environment variable dynamically

```sh
oc set env deploy/myapp -e ENVAR=W00t!
oc set env deploy/myapp -e URI=https://api.finto.fi
oc set env deploy/myapp -e URI_PARAMS='/rest/v1/search?vocab=ysa&query=kiss*&lang=fi'
oc set env deploy/myapp -e USER_KEY=myuserkey
```

## 5. Create a base64 password

```sh
python -c "import base64; import hashlib; import getpass; print(base64.b64encode(hashlib.sha1(str.encode(getpass.getpass())).digest()))"
```

## 6. Create secret.json

```sh
cat <<EOF >secret.json
apiVersion: v1
kind: Secret
metadata:
  name: mysecret
type: Opaque 
data:
  password: ${BASE64_PASSWORD}
EOF
```
## 7. Create secret

```sh
oc create -f secret.json
```


# References

https://docs.openshift.com/container-platform/3.11/dev_guide/dev_tutorials/binary_builds.html
https://docs.openshift.com/online/pro/using_images/s2i_images/java.html#s2i-images-java-deploy-applications
http://v1.uncontained.io/playbooks/app_dev/binary_deployment_howto.html
