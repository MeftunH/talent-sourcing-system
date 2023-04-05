![TSS-logo](https://user-images.githubusercontent.com/48466124/229839845-0c3998ab-7068-4163-9598-5afdb506e11b.png)

# TSS
This project is simple talent sourcing system.

## Related Repositories
- [TSS-frontend](https://github.com/MeftunH/talent-sourcing-system-frontend)

## Installation For Backend Spring Boot
- Clone the project
-  Gradle build
- `./gradlew build`
- `./gradlew bootRun`

## Installation For Frontend
- Clone the project
- Run `npm install` to install dependencies
- Run `npm start` to start the project
- Run `npm run build` to build the project

## Technologies
- Java 17
- Spring 6
- Javascript
- Spring Boot 3
- Spring Data JPA
- PostgresSQL
- React
- Redux
- Tailwind CSS

## Database ER Diagram
![Database ER diagram (crow's foot)](https://user-images.githubusercontent.com/48466124/229949399-21c85145-c76f-4a7b-bd3f-070ff6061113.png)

## High Level System Design of TSS
![dof-tss](https://user-images.githubusercontent.com/48466124/229918371-5bacb05b-f57a-43ce-a10b-ed1926d2f4de.png)

## Logical Hierarchy
![logical](https://user-images.githubusercontent.com/48466124/229946094-b30d8103-52c7-4313-831a-e060d8372a40.png)


## Module Hierarchy
https://excalidraw.com/#json=1rl_PWZalzLl5hD4Y2pKN,QT5TPBu4S5LgmTUMPaln-Q
![img.png](git/img.png)



## API Documentation
### Base URL
`
http://localhost:8081/swagger-ui/index.html
`
![image](https://user-images.githubusercontent.com/48466124/229953817-f1577ea8-6a8f-4dfb-b225-8933bfed965a.png)
![image](https://user-images.githubusercontent.com/48466124/229953843-db731122-0054-4def-b021-ee09cf243967.png)
![image](https://user-images.githubusercontent.com/48466124/229953860-0d346b9f-22a6-453c-9c70-aea3bed08cc4.png)


## Request And Response
### Add Candidate
Request

`
 method: POST
`
`
 url: http://localhost:8081/api/v1/candidates
`
```
    {
     "candidateStatus": "SOURCED",
	"name": "a",
	"surname": "Hashimli",
	"personType": "CANDIDATE"
	}
```
Response
```
{
	"data": {
		"id": 902,
		"name": "a",
		"surname": "Hashimli",
		"personType": "CANDIDATE",
		"contactInformation": null,
		"candidateStatus": "SOURCED",
		"baseAdditionalFieldsCreatedDate": "2023/04/04",
		"baseAdditionalFieldsUpdatedDate": "2023/04/04"
	},
	"responseDate": "2023-04-04T23:12:10.313+00:00",
	"message": null,
	"success": true
}
```
When fields are null
```agsl
{
  "candidateStatus": "SOURCED",
	"name": null,
	"surname": "Hashimli",
	"personType": "CANDIDATE"
}
```
Response
```
{
	"data": {
		"errorDate": "2023-04-05T00:23:07.724+00:00",
		"message": "Person Fields Cannot Be Null!",
		"detail": "Please check the field."
	},
	"responseDate": "2023-04-05T00:23:07.724+00:00",
	"message": "Person Fields Cannot Be Null!",
	"success": false
}
```
### Get Candidate
Request
```agsl
method: GET
url: http://localhost:8081/api/v1/candidates/902
```
Response
```agsl
{
    "data": {
        "id": 902,
        "name": "a",
        "surname": "Hashimli",
        "personType": "CANDIDATE",
        "contactInformation": null,
        "candidateStatus": "SOURCED",
        "baseAdditionalFieldsCreatedDate": "2023/04/04",
        "baseAdditionalFieldsUpdatedDate": "2023/04/04"
    },
    "responseDate": "2023-04-04T23:12:10.313+00:00",
    "message": null,
    "success": true
}
```
When candidate not found
```agsl
method: GET
url: http://localhost:8081/api/v1/candidates/1
```
Response
```agsl
{
    "data": {
        "errorDate": "2023-04-05T00:23:07.724+00:00",
        "message": "Candidate Not Found!",
        "detail": "Please check the candidate id."
    },
    "responseDate": "2023-04-05T00:23:07.724+00:00",
    "message": "Candidate Not Found!",
    "success": false
}
```
### Get All Candidates
Request
```agsl
method: GET
url: http://localhost:8081/api/v1/candidates
```
Response
```agsl
{
	"data": [
		{
			"id": 52,
			"name": "Maftun",
			"surname": "Hashimli",
			"personType": "CANDIDATE",
			"contactInformation": [],
			"candidateStatus": "SOURCED",
			"baseAdditionalFieldsCreatedDate": "2023/04/02",
			"baseAdditionalFieldsUpdatedDate": "2023/04/02"
		},
		{
			"id": 102,
			"name": "Maftun",
			"surname": "Hashimli",
			"personType": "CANDIDATE",
			"contactInformation": [],
			"candidateStatus": "SOURCED",
			"baseAdditionalFieldsCreatedDate": "2023/04/02",
			"baseAdditionalFieldsUpdatedDate": "2023/04/02"
		},
		{
			"id": 152,
			"name": "Maftun",
			"surname": "Hashimli",
			"personType": "CANDIDATE",
			"contactInformation": [
				{
					"contactInformationType": null,
					"candidateId": null,
					"phoneNumber": null,
					"emailAddress": null
				}
			],
			"candidateStatus": "SOURCED",
			"baseAdditionalFieldsCreatedDate": "2023/04/02",
			"baseAdditionalFieldsUpdatedDate": "2023/04/02"
		},
		{
			"id": 202,
			"name": "Maftun",
			"surname": "Hashimli",
			"personType": "CANDIDATE",
			"contactInformation": [],
			"candidateStatus": "SOURCED",
			"baseAdditionalFieldsCreatedDate": "2023/04/02",
			"baseAdditionalFieldsUpdatedDate": "2023/04/02"
		},
		{
			"id": 252,
			"name": "Maftun",
			"surname": "Hashimli",
			"personType": "CANDIDATE",
			"contactInformation": [],
			"candidateStatus": "SOURCED",
			"baseAdditionalFieldsCreatedDate": "2023/04/02",
			"baseAdditionalFieldsUpdatedDate": "2023/04/02"
		},
		{
			"id": 253,
			"name": "Maftun",
			"surname": "Hashimli",
			"personType": "CANDIDATE",
			"contactInformation": [],
			"candidateStatus": "SOURCED",
			"baseAdditionalFieldsCreatedDate": "2023/04/02",
			"baseAdditionalFieldsUpdatedDate": "2023/04/02"
		}
	],
	"responseDate": "2023-04-02T21:14:19.980+00:00",
	"message": null,
	"success": true
}
```
### Update Candidate
Request
```agsl
method: PUT
url: http://localhost:8081/api/v1/candidates/902
```
```agsl
{
    "candidateStatus": "SOURCED",
    "name": "a",
    "surname": "Hashimli",
    "personType": "CANDIDATE"
}
```
Response
```agsl
{
    "data": {
        "id": 902,
        "name": "a",
        "surname": "Hashimli",
        "personType": "CANDIDATE",
        "contactInformation": null,
        "candidateStatus": "SOURCED",
        "baseAdditionalFieldsCreatedDate": "2023/04/04",
        "baseAdditionalFieldsUpdatedDate": "2023/04/04"
    },
    "responseDate": "2023-04-04T23:12:10.313+00:00",
    "message": null,
    "success": true
}
```
### Update Candidate's Status
Request
```agsl
method: PATCH
url: http://localhost:8081/api/v1/candidates/52/status
```
```agsl
{
    "candidateStatus": "SOURCED"
}
```
Response
```agsl
{
	"data": {
		"id": 52,
		"name": "Maftun",
		"surname": "Hashimli",
		"personType": "CANDIDATE",
		"contactInformation": [],
		"candidateStatus": "SOURCED",
		"baseAdditionalFieldsCreatedDate": "2023/04/02",
		"baseAdditionalFieldsUpdatedDate": "2023/04/02"
	},
	"responseDate": "2023-04-02T02:54:32.148+00:00",
	"message": null,
	"success": true
}
```
When candidate status is not valid
```agsl
{
    "data": null,
    "responseDate": "2023-04-02T02:55:12.000+00:00",
    "message": "Candidate Status is not valid!",
    "success": false
}
```
When Candidate is not found
```agsl
{
    "data": null,
    "responseDate": "2023-04-02T02:55:12.000+00:00",
    "message": "Candidate is not found!",
    "success": false
}
```
### Delete Candidate
Request
```agsl
method: DELETE
url: http://localhost:8081/api/v1/candidates/52
```
Response
```agsl
{
	"data": null,
	"responseDate": "2023-04-05T00:30:26.439+00:00",
	"message": null,
	"success": true
}
```
When Candidate is not found
```agsl
{
    "data": null,
    "responseDate": "2023-04-02T02:55:12.000+00:00",
    "message": "Candidate is not found!",
    "success": false
}
```
### Save Candidate's Contact Information
### With Both Email and Phone Number
Request
```agsl
method: POST
url: http://localhost:8081/api/v1/contact-informations
```
```agsl
{
  "contactInformationType": "BOTH",
  "candidateId": 902,
	"emailAddress": "dffddf@gmail.com",
	"phoneNumber": "6666666666"
}
```
Response
```agsl
{
	"data": {
		"id": 302,
		"contactInformationType": "BOTH",
		"candidateId": 902,
		"phoneNumber": "6666666666",
		"emailAddress": "dffddf@gmail.com",
		"baseAdditionalFieldsCreatedDate": "2023/04/04",
		"baseAdditionalFieldsUpdatedDate": "2023/04/04"
	},
	"responseDate": "2023-04-04T23:12:18.961+00:00",
	"message": null,
	"success": true
}
```
When phone number is not valid
```agsl
{
  "contactInformationType": "BOTH",
  "candidateId": 904,
	"emailAddress": "dffddf@gmail.com",
	"phoneNumber": "11"
}
```
Response
```agsl
{
	"data": {
		"errorDate": "2023-04-05T00:33:42.461+00:00",
		"message": "Phone number is not valid",
		"detail": "Please re-check the phone number"
	},
	"responseDate": "2023-04-05T00:33:42.461+00:00",
	"message": "Phone number is not valid",
	"success": false
}
```
When email address is not valid
```agsl
{
  "contactInformationType": "BOTH",
  "candidateId": 904,
	"emailAddress": "aa",
	"phoneNumber": "6666666666"
}
```
Response
```agsl
{
	"data": {
		"errorDate": "2023-04-05T00:34:30.026+00:00",
		"message": "Email is not valid",
		"detail": "Please re-check the email"
	},
	"responseDate": "2023-04-05T00:34:30.026+00:00",
	"message": "Email is not valid",
	"success": false
}
```
### With Only Email
Request
```agsl
method: POST
url: http://localhost:8081/api/v1/contact-informations
```
```agsl
{
  "contactInformationType": "EMAIL",
  "candidateId": 904,
	"emailAddress": "aa@gmail.com"

}
```
Response
```agsl
{
	"data": {
		"id": 352,
		"contactInformationType": "EMAIL",
		"candidateId": 904,
		"phoneNumber": null,
		"emailAddress": "aa@gmail.com",
		"baseAdditionalFieldsCreatedDate": "2023/04/05",
		"baseAdditionalFieldsUpdatedDate": "2023/04/05"
	},
	"responseDate": "2023-04-05T00:38:41.982+00:00",
	"message": null,
	"success": true
}
```
### With Only Phone Number
Request
```agsl
method: POST
url: http://localhost:8081/api/v1/contact-informations
```
```agsl
{
  "contactInformationType": "PHONE_NUMBER",
  "candidateId": 904,
	"phoneNumber": "7777777777"
}
```
Response
```agsl
{
	"data": {
		"id": 353,
		"contactInformationType": "PHONE_NUMBER",
		"candidateId": 904,
		"phoneNumber": "7777777777",
		"emailAddress": null,
		"baseAdditionalFieldsCreatedDate": "2023/04/05",
		"baseAdditionalFieldsUpdatedDate": "2023/04/05"
	},
	"responseDate": "2023-04-05T00:39:33.055+00:00",
	"message": null,
	"success": true
}
```

### Update Candidate's Contact Information
 Creation validation rules are applied for update as well.
### With Both Email and Phone Number
Request
```agsl
method: PUT
url: http://localhost:8081/api/v1/contact-informations
```
```agsl
{
  "contactInformationType": "BOTH",
  "candidateId": 904,
    "emailAddress": "loop@gmail.com"
    "phoneNumber": "9999999999"
}
```
Response
```agsl
{
	"data": {
		"id": 104,
		"contactInformationType": "BOTH",
		"candidateId": 904,
		"phoneNumber": "9999999999",
		"emailAddress": "loop@gmail.com",
		"baseAdditionalFieldsCreatedDate": "2023/04/03",
		"baseAdditionalFieldsUpdatedDate": "2023/04/05"
	},
	"responseDate": "2023-04-05T00:41:28.616+00:00",
	"message": null,
	"success": true
}
```
### With Only Email
Request
```agsl
method: PUT
url: http://localhost:8081/api/v1/contact-informations
```
```agsl
{
  "contactInformationType": "EMAIL",
  "candidateId": 904,
    "emailAddress": "asa@gmail.com"
}
```
Response
```agsl
{
    "data": {
        "id": 104,
        "contactInformationType": "EMAIL",
        "candidateId": 904,
        "phoneNumber": null,
        "emailAddress": "asa@gmail.com",
        "baseAdditionalFieldsCreatedDate": "2023/04/03",
        "baseAdditionalFieldsUpdatedDate": "2023/04/05"
    },
    "responseDate": "2023-04-05T00:42:19.123+00:00",
    "message": null,
    "success": true
}
```

## Get Interaction By Id
Request
```agsl
method: GET
url: http://localhost:8081/api/v1/interactions/1
```
 Response
```agsl
{
	"data": {
		"baseAdditionalFieldsCreatedDate": "2023/04/01",
		"baseAdditionalFieldsUpdatedDate": "2023/04/03",
		"id": 1,
		"content": "restfulToolkitX",
		"isCandidateResponded": "NO",
		"interactionType": "EMAIL",
		"candidateId": null
	},
	"responseDate": "2023-04-05T00:46:58.300+00:00",
	"message": null,
	"success": true
}
```
## Get Interactions By Candidate Id
Request
```agsl
method: GET
url: http://localhost:8081/api/v1/interactions/candidate/52
```
 Response
```agsl
{
    "data": [
      	{
			"baseAdditionalFieldsCreatedDate": "2023/04/01",
			"baseAdditionalFieldsUpdatedDate": "2023/04/02",
			"id": 53,
			"content": "ggjhjj",
			"isCandidateResponded": "NO",
			"interactionType": "EMAIL",
			"candidateId": null
		},
		{
			"baseAdditionalFieldsCreatedDate": "2023/04/01",
			"baseAdditionalFieldsUpdatedDate": "2023/04/02",
			"id": 5,
			"content": "udpated",
			"isCandidateResponded": "YES",
			"interactionType": "PHONE",
			"candidateId": null
		}
	],
	"responseDate": "2023-04-03T03:55:39.552+00:00",
	"message": null,
	"success": true
}
```
### Update Interaction By Id
Request
```agsl
method: PUT
 ```
```agsl
url: http://localhost:8081/api/v1/interactions/5
```
```agsl
{
  "content": "udpated",
  "isCandidateResponded": "YES",
  "interactionType": "PHONE"
}
```
Response
```agsl
{
  {
	"data": {
		"baseAdditionalFieldsCreatedDate": "2023/04/01",
		"baseAdditionalFieldsUpdatedDate": "2023/04/02",
		"id": 5,
		"content": "udpated",
		"isCandidateResponded": "YES",
		"interactionType": "PHONE",
		"candidateId": null
	},
	"responseDate": "2023-04-02T02:31:55.672+00:00",
	"message": null,
	"success": true
}
```
When interaction is not found

`url:
http://localhost:8081/api/v1/interactions/100`
```agsl
{
    "data": null,
    "responseDate": "2023-04-02T02:32:55.672+00:00",
    "message": "Interaction not found",
    "success": false
}
```
When field is not valid
Request
```agsl
{
  "content": null,
  "isCandidateResponded": "YES",
  "interactionType": "PHONE"
}
```
Response
```agsl
{
	"data": {
		"errorDate": "2023-04-05T00:58:47.793+00:00",
		"message": "Interaction fields must be not null",
		"detail": "Please check the interaction fields"
	},
	"responseDate": "2023-04-05T00:58:47.793+00:00",
	"message": "Interaction fields must be not null",
	"success": false
}
```
### Delete Interaction By Id
 `url: http://localhost:8081/api/v1/interactions/52 `
Response
```agsl
{
	"data": null,
	"responseDate": "2023-04-05T00:30:26.439+00:00",
	"message": null,
	"success": true
}
```

When interaction is not found
```agsl
{
	"data": {
		"errorDate": "2023-04-05T01:00:41.042+00:00",
		"message": "Item not found!",
		"detail": "Please check the id of the item."
	},
	"responseDate": "2023-04-05T01:00:41.042+00:00",
	"message": "Item not found!",
	"success": false
}
```
## Testing
### Unit Test
![image](https://user-images.githubusercontent.com/48466124/229955025-49138a65-2b8a-420e-9cce-f6f71ef11fcf.png)
![image](https://user-images.githubusercontent.com/48466124/229955048-7d2a88af-a33d-460b-8a4f-d3e0e6b7edc9.png)

### Integration Test
![image](https://user-images.githubusercontent.com/48466124/229955125-12dd88a9-413f-4293-9c0a-9f5d39d48530.png)

## Screens
Home Page
![image](https://user-images.githubusercontent.com/48466124/229908053-ecd7a212-75e2-4d24-b246-78353da06879.png)

Add Candidate
![image](https://user-images.githubusercontent.com/48466124/229842255-a3842b03-cad8-45e6-b991-fd484557b76f.png)
Successfully added candidate
![image](https://user-images.githubusercontent.com/48466124/229842420-ba98e3a9-5dc9-4a7d-bfa3-25c18bb0b562.png)
Form Validation
![image](https://user-images.githubusercontent.com/48466124/229842638-3926353c-406c-4757-a17f-07d2ad708612.png)
Show Contact Information Only Email
![image](https://user-images.githubusercontent.com/48466124/229848372-c782ff39-f7b5-474d-8eec-a1783567c06c.png)
Show Contact Information Only Phone
![image](https://user-images.githubusercontent.com/48466124/229848657-d4d2e600-d1b1-4996-85c1-83de2ebdeef2.png)
Show Contact Information Both Email and Phone
![image](https://user-images.githubusercontent.com/48466124/229848526-bc180014-1aba-4b8b-9cef-c49f038f458a.png)


Add Contact Information 

![image](https://user-images.githubusercontent.com/48466124/229849613-66b5ee3c-c517-4498-b921-948bd9198fa6.png)


Types

![image](https://user-images.githubusercontent.com/48466124/229901537-eb49b509-bbcf-46cd-a8cb-f4a378305789.png)When you select email type

![image](https://user-images.githubusercontent.com/48466124/229849920-ab0e195d-f740-4a44-b97e-6ab8e6f04139.png)

When you select phone type

![image](https://user-images.githubusercontent.com/48466124/229850104-51623586-2803-4999-8e69-856aa55ae474.png)

When you select both email and phone type

![image](https://user-images.githubusercontent.com/48466124/229850224-83aa0747-d2da-49aa-a983-e5cb08c92ecc.png)



When Contact Information is already added

![image](https://user-images.githubusercontent.com/48466124/229850335-bcb6a717-537c-47af-ac96-4ec51f301a59.png)

Update Candidate Status

![image](https://user-images.githubusercontent.com/48466124/229907679-60b196d2-087c-44fa-87f0-30b83b237936.png)

Edit Contact Information

![image](https://user-images.githubusercontent.com/48466124/229842763-e0ffb2fd-b157-4870-9efc-8b26241c78a2.png)

![image](https://user-images.githubusercontent.com/48466124/229842809-b9c66ca0-4451-4834-b34f-9e630a5efb4c.png)

![image](https://user-images.githubusercontent.com/48466124/229842836-ec4f6863-180a-484b-903f-9cd41bcae1d3.png)

Edit Candidate

![image](https://user-images.githubusercontent.com/48466124/229908300-1586a026-a38c-4282-8647-831d8a6c24e8.png)

![image](https://user-images.githubusercontent.com/48466124/229908391-639b9d9b-ee9a-470e-8917-1168c725645b.png)


Interactions

![image](https://user-images.githubusercontent.com/48466124/229908476-3cf1d271-78c2-4574-8035-60935fa1ea75.png)

When candidate has no interactions

![image](https://user-images.githubusercontent.com/48466124/229908576-27c77b5f-2686-443a-b4d9-04e923a55b84.png)

Create Interaction

![image](https://user-images.githubusercontent.com/48466124/229908727-a0a346f3-56dc-4a9b-9f23-b9d8db104a5d.png)

![image](https://user-images.githubusercontent.com/48466124/229908798-34731547-978f-437f-98bd-6a803f7601e5.png)

![image](https://user-images.githubusercontent.com/48466124/229908826-9f03c635-a06c-4129-b6ce-d9036a5962d8.png)

Update Interaction

![image](https://user-images.githubusercontent.com/48466124/229908905-1f22bf21-f204-435e-8417-56f6d53790ea.png)

![image](https://user-images.githubusercontent.com/48466124/229908997-d5a13a1b-eaf6-495a-ac1d-74548c5b1992.png)

![image](https://user-images.githubusercontent.com/48466124/229909107-6782de9c-3b7e-4651-a5b3-31590f5c5953.png)



