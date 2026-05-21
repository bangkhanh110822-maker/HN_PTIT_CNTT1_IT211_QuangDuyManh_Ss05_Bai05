# ProductAPI_JPA

## 2. Test script cho từng request

### 2.1 POST `/api/v1/products`
Mục đích: tạo mới sản phẩm.

```javascript
pm.test("Status code is 201", () => pm.response.to.have.status(201));
pm.test("Response has id", () => pm.expect(pm.response.json()).to.have.property("id"));

const created = pm.response.json();
pm.collectionVariables.set("productId", created.id);
```

### 2.2 GET `/api/v1/products`
Mục đích: lấy toàn bộ danh sách sản phẩm.

```javascript
pm.test("Status code is 200", () => pm.response.to.have.status(200));
pm.test("Response is an array", () => pm.expect(pm.response.json()).to.be.an("array"));
```

### 2.3 GET `/api/v1/products/{id}`
Mục đích: lấy sản phẩm theo id.

```javascript
pm.test("Status code is 200", () => pm.response.to.have.status(200));
pm.test("Response contains id", () => pm.expect(pm.response.json()).to.have.property("id"));
```

### 2.4 GET `/api/v1/products/{id}` không tồn tại
Mục đích: kiểm tra trường hợp thất bại.

```javascript
pm.test("Status code is 404", () => pm.response.to.have.status(404));
```

### 2.5 POST `/api/v1/products` với dữ liệu không hợp lệ
Mục đích: kiểm tra validation.

```javascript
pm.test("Status code is 400", () => pm.response.to.have.status(400));
pm.test("Response has validation fields", () => pm.expect(pm.response.json()).to.have.property("productName"));
```

### 2.6 PUT `/api/v1/products/{id}`
Mục đích: cập nhật toàn bộ sản phẩm.

```javascript
pm.test("Status code is 200", () => pm.response.to.have.status(200));
pm.test("Response contains id", () => pm.expect(pm.response.json()).to.have.property("id"));
```

### 2.7 PATCH `/api/v1/products/{id}`
Mục đích: cập nhật một phần sản phẩm.

```javascript
pm.test("Status code is 200", () => pm.response.to.have.status(200));
pm.test("Response contains id", () => pm.expect(pm.response.json()).to.have.property("id"));
```

### 2.8 DELETE `/api/v1/products/{id}`
Mục đích: xóa sản phẩm.

```javascript
pm.test("Status code is 200", () => pm.response.to.have.status(200));
```

## 6. Tài liệu API ngắn

### Base URL

```text
http://localhost:8080/api/v1/products
```

### Danh sách endpoint

| Method | Endpoint | Mô tả | Status |
| --- | --- | --- | --- |
| GET | `/api/v1/products` | Lấy tất cả sản phẩm | 200 |
| GET | `/api/v1/products/{id}` | Lấy sản phẩm theo id | 200 |
| GET | `/api/v1/products/{id}` | Không tìm thấy sản phẩm | 404 |
| POST | `/api/v1/products` | Tạo mới sản phẩm | 201 |
| POST | `/api/v1/products` | Dữ liệu không hợp lệ | 400 |
| PUT | `/api/v1/products/{id}` | Cập nhật toàn bộ sản phẩm | 200 |
| PATCH | `/api/v1/products/{id}` | Cập nhật một phần sản phẩm | 200 |
| DELETE | `/api/v1/products/{id}` | Xóa sản phẩm | 200 |

### Request body mẫu

#### Tạo mới / cập nhật

```json
{
  "productName": "Sach Java",
  "description": "Hoc Spring Boot",
  "price": 30000,
  "stock": 10
}
```

#### Validation fail

```json
{
  "productName": "",
  "description": "",
  "price": null,
  "stock": null
}
```

#### Patch update

```json
{
  "productName": "Sach Java Update",
  "price": 40000
}
```

### Response mẫu

#### POST thành công

```json
{
  "id": 1,
  "productName": "Sach Java",
  "description": "Hoc Spring Boot",
  "price": 30000,
  "stock": 10
}
```

#### GET all

```json
[
  {
    "id": 1,
    "productName": "Sach Java",
    "description": "Hoc Spring Boot",
    "price": 30000,
    "stock": 10
  }
]
```

#### GET by id không tồn tại

```text
Khong tim thay product co id = 999
```

#### DELETE thành công

```text
Xoa san pham thanh cong
```

### Ghi chú

- API hiện tại dùng path: `/api/v1/products`
- Khi `POST` thành công, lấy `id` từ response và lưu vào biến collection:

```javascript
pm.collectionVariables.set("productId", pm.response.json().id);
```

- Có thể dùng biến đó trong các request sau:

```text
{{productId}}
```

### Thứ tự chạy đề xuất trong Runner

1. POST Create
2. GET All
3. GET By Id
4. GET By Id False
5. POST Invalid
6. PUT Update
7. PATCH Update
8. DELETE
