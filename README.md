
# Real Time Event Ticketing Application

This project is real time ticket releasing and tickets retrieving application.This enables to do buying and selling process concurrently.The architecture is Producer-Consumer architecture.


## API Reference

#### Start 

```http
  POST /api/v1/execute/${iconfiguration numd}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `configuration num` | `Integer` | **Required**.Start application with configuration number |

#### Stop

```http
  POST /api/v1/execute/stop
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `N/A`      | `N/A` | Stop the application |

#### Add configuratio

```http
  POST /api/v1/config/addConfig
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| Request Body      | `Request Body` | Add Configurations |

#### Add Vendors
```http
  POST /api/v1/vendor/addVendor
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| Request Body      | `Request Body` | Add Vendors |
#### Add Customers
```http
  POST /api/v1/customer/addCustomer
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| Request Body      | `Request Body` | Add Customers |



## Authors

- [@vimukthiwaththegama](https://github.com/vimukthiwaththegama)


## Appendix

This has a simulation part and manually customers and vendors adding part in the front-end,And simulation part is started before the spring application and,After it finished spring application will start.


## Features

- Seperate simulation part
- Seperate manual part
- Can add vendors,customers, configurations and can maintain them in a basic database(Json files)


## Used By

This project is for following users:

- Event Admin(Event Manager/Application Admin)


