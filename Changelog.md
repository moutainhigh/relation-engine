

# 添加人
MERGE (p1:Person{ name:"李云龙"})
MERGE (p2:Person{ name:"乔小强"})
MERGE (p3:Person{ name:"李洁"})
MERGE (p4:Person{ name:"郭文远"})



# 添加手机
MERGE (m1:Mobile{ phoneNumber:"18651698213"})
MERGE (m2:Mobile{ phoneNumber:"18600000000"})
MERGE (m3:Mobile{ phoneNumber:"18611111111"})
MERGE (m4:Mobile{ phoneNumber:"01060554540"})
MERGE (m5:Mobile{ phoneNumber:"18612464139"})
MERGE (m6:Mobile{ phoneNumber:"18622222222"})


# 查询

### by ID 
MATCH (n:Person) where  ID(n)=1126 return n


### where
MATCH (p:Person) , (m:Mobile)
where p.name = "李云龙" and m.phoneNumber = "18651698213"
RETURN p,m

### by mobile
MATCH (p:Person)-[:OWNER]-(m:Mobile)
where p.name = "赵晓丹" 
RETURN p,m


# 建立关系

###本人手机
MATCH (p:Person) , (m:Mobile)
where p.name = "李云龙" and m.phoneNumber = "18651698213"
CREATE (m)-[r:OWNER]->(p) 
RETURN r

MATCH (p:Person) , (m:Mobile)
where p.name = "乔小强" and m.phoneNumber = "18600000000"
CREATE (m)-[r:OWNER]->(p) 
RETURN r

MATCH (p:Person) , (m:Mobile)
where p.name = "李洁" and m.phoneNumber = "18611111111"
CREATE (m)-[r:OWNER]->(p) 
RETURN r


MATCH (p:Person) , (m:Mobile)
where p.name = "郭文远" and m.phoneNumber = "18622222222"
CREATE (m)-[r:OWNER]->(p) 
RETURN r
### 通话记录

MATCH (p:Person) , (m:Mobile)
where p.name = "李云龙" and m.phoneNumber = "01060554540"
CREATE (p)-[r:CALLRECORD{ duration:"30", tel:"01060554540", time:""}]->(m) 
RETURN r


MATCH (p:Person) , (m:Mobile)
where p.name = "乔小强" and m.phoneNumber = "01060554540"
CREATE (p)-[r:CALLRECORD{ duration:"40", tel:"01060554540", time:""}]->(m) 
RETURN r


### 通讯录

MATCH (p:Person) , (m:Mobile)
where p.name = "李云龙" and m.phoneNumber = "18600000000"
CREATE (p)-[r:ADDRESSBOOK{ name:"小强", tel:"18600000000"}]->(m) 
RETURN r

MATCH (p:Person) , (m:Mobile)
where p.name = "李云龙" and m.phoneNumber = "18611111111"
CREATE (p)-[r:ADDRESSBOOK{ name:"李洁", tel:"18611111111"}]->(m) 
RETURN r

MATCH (p:Person) , (m:Mobile)
where p.name = "乔小强" and m.phoneNumber = "18611111111"
CREATE (p)-[r:ADDRESSBOOK{ name:"洁1", tel:"18611111111"}]->(m) 
RETURN r

MATCH (p:Person) , (m:Mobile)
where p.name = "乔小强" and m.phoneNumber = "18622222222"
CREATE (p)-[r:ADDRESSBOOK{ name:"文远", tel:"18622222222"}]->(m) 
RETURN r


MATCH (p:Person) , (m:Mobile)
where p.name = "郭文远" and m.phoneNumber = "18611111111"
CREATE (p)-[r:ADDRESSBOOK{ name:"洁洁", tel:"18611111111"}]->(m) 
RETURN r

MATCH (p:Person) , (m:Mobile)
where p.name = "郭文远" and m.phoneNumber = "18600000000"
CREATE (p)-[r:ADDRESSBOOK{ name:"洁洁", tel:"18600000000"}]->(m) 
RETURN r

# 一度人脉
* 通讯录 （未被测评过）

* 通讯录 （目标---被测评过）
```neo4j
MATCH (a:Person )-[:ADDRESSBOOK]->(ADDRESSBOOK)-[:OWNER]->(b:Person) 
where ID(a)=1126  
return b
```
* 通讯录
```
MATCH (n:Person {name:"赵晓丹"} )-[c]-(m:Mobile)  with n,m,c
return    n,m,c
```

# 可能认识

* 可能认识（对方通讯录记录了我的号码；拥有我的号码，但不是好友（他有我的手机号码））
```
MATCH (a:Person {name:"乔小强"})<-[:owner]->(m)<-[:addressbook]-(b:Person) with m,b
where not (a)-[:addressbook]->()-[:owner]->(b) 
return b,m

MATCH (a:Person )<-[:owner]->(m)<-[:addressbook]-(b:Person) with m,b
where not (a)-[:addressbook]->()-[:owner]->(b) and ID(a)=1128
return b,m

```
* 可能认识（我们都有同一个手机号码）
```
MATCH (n:Person {name:"李云龙"})-[:ADDRESSBOOK]->(m)<-[:ADDRESSBOOK]-(b) with n,m,b 
where not (n)-[:ADDRESSBOOK]->()-[:OWNER]->(b)
return    b,m,n

MATCH (n:Person )-[:addressbook]->(m)<-[:addressbook]-(b) with n,m,b 
where not (n)-[:addressbook]->()-[:owner]->(b) and ID(a)=1126
return    b,m,n
```
* 可能认识（对方给我打过电话 & 最近24小时 打过电话）
```

```

* 有N个共同好友
```neo4j
MATCH (a:Person )-[:addressbook]->(addressbook)<-[:addressbook]-(b:Person) 
where ID(a)=1126  
RETURN b.ID AS id,b.name, COUNT(*) AS comCount,4 AS type   
ORDER BY comCount DESC SKIP 1  LIMIT 3
```

# 二度人脉
* 


# 最短路径
* 最短路径
`
MATCH p=allShortestPaths((a:Person {name:"李云龙"})-[:addressbook*..4]-(b:Person {name:"郭文远"})) 
RETURN p
`



match (n:Person {name:"李云龙"})-[:addressbook]->(m)-[:addressbook]->(b) 

return b



# 踩过的坑 ：

* 服务器句柄数设置
Starting Neo4j.
WARNING: Max 1024 open files allowed, minimum of 40000 recommended. See the Neo4j manual.

* 导入数据——明确指定相关ID
    这里写图片描述

* 导入数据——重要参数设置，否则莫名毫无提示的停止导入
    –skip-bad-relationships=true
    –stacktrace
    –bad-tolerance=50000
    –skip-duplicate-nodes=true
    版本兼容之OPTION MATCH 正确使用，参考https://dzone.com/articles/new-neo4j-optional
* LOAD CSV超级慢


MATCH (n:Person {name:"李云龙"})-[:addressbook*1..2]-(neighbors) 
RETURN collect(DISTINCT neighbors)


MATCH p=allShortestPaths((a:Person {name:"李云龙"})-[:addressbook*..4]-(b:Person {name:"郭文远"})) 
RETURN p



# Delete
```
MATCH (e:Mobile)-[ADDRESSBOOK]-(c:Person) with e,ADDRESSBOOK,c  where e.phoneNumber="4008308300" or e.phoneNumber = "15210770667" delete e,ADDRESSBOOK,c

```

* 查询某人 二度好友
```
MATCH (n:Person {name:"赵晓丹"} )-[ADDRESSBOOK]->(m)<-[ADDRESSBOOK1]-(n2:Person)  with n,m,n2

return    n,m,n2
```
