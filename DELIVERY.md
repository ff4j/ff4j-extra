
 # Installation Guide

Here is the following procedure to deliver new version of ff4j.

- First be sure to be update to date with GITHUB

```shell
git status
git pull
```

- If a git TAG is already created

```shell
git tag -d 1.7.1
git push origin
```

- Start git agent (Windows)
```shell
eval `ssh-agent.exe`
cd
cd .ssh
ssh-add github
ssh-add -l
ff4j
```

- Start
```shell
mvn clean release:prepare release:perform
```
