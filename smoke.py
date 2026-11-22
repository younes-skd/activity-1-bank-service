#!/usr/bin/env python3
import json, urllib.request, sys
def req(port,path,body=None,method=None):
    request=urllib.request.Request(f'http://localhost:{port}{path}',data=json.dumps(body).encode() if body is not None else None,method=method,headers={'Content-Type':'application/json'})
    with urllib.request.urlopen(request,timeout=180) as response:
        data=response.read();return json.loads(data) if data else None

data=req(8081,'/api/comptes');assert len(data)>=2
c=req(8081,'/api/comptes',{'balance':321,'currency':'MAD','type':'SAVING'});assert c['balance']==321
assert req(8081,'/graphql',{'query':'{ comptes { id balance currency } }'})['data']['comptes']
assert req(8081,'/data/comptes?projection=summary')['_embedded']['comptes']
req(8081,'/api/comptes/'+c['id'],method='DELETE')
print('PASS: REST, GraphQL and Spring Data REST projection')
