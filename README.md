# mydata

## Database access
url: http://localhost:8080/console/

user:sa

pwd:

Database:H2



## API Contract (curls)

CREATE RESOURCE curl:
curl -X POST \
  http://localhost:8080/mydata \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 80fa6ff2-6088-4d4c-6afd-b0f4af421757' \
  -d '{
   "timestamp":1632164028,
   "attributes": [
	{
	    "key" : "1",
	    "value": "1"
	},
	{
	    "key" : "2",
	    "value": "2"
	}
	]
}'
	  
GET RESOURCE curl:
curl -X GET \
  http://localhost:8080/mydata/1632164028/1632310651/2/2 \
  -H 'cache-control: no-cache' \
  -H 'postman-token: b6c4c433-c125-cc43-9a44-f84b181d4043'
  

