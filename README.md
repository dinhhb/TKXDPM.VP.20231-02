# TKXDPM.VN.20231-02
This is a Capstone's source code for Software Design and Construction project

## Team member

| Name              | Role        |
|:------------------| :---------- |
| Cao Thành Duy     | Team Leader |
| Hà Bửu Định       | Member      |
| Nguyễn Đình Dương | Member      |


## Report Content


<details>
  <summary>Week 1: 28/11/2023 ~ 4/12/2023</summary>
<br>
<details>
<summary>Cao Thành Duy</summary>

- Assigned tasks:
    - Build VnPay payment

- Implementation details:
    - Pull Request(s): https://github.com/dinhhb/TKXDPM.VP.20231-02/pull/3
    - Specific implementation details:
        - Build VnPay connection

</details>

<details>
<summary>Hà Bửu Định</summary>

- Assigned tasks:
    - Build usecase ViewCart

- Implementation details:
    - Pull Request(s): https://github.com/dinhhb/TKXDPM.VP.20231-02/pull/2
    - Specific implementation details:
        - Build controller, view handler for use case ViewCart
        - Successfully show ViewCart screen but cannot display media in cart because of some errors

</details>


<details>
<summary>Nguyễn Đình Dương</summary>

- Assigned tasks:
    - Build database
    - Build base screen invoice

- Implementation details:
    - Pull Request(s): https://github.com/dinhhb/TKXDPM.VP.20231-02/pull/4
    - Specific implementation details:
        - Build database, connect db and test query
        - Build view and handler for screen invoice but not have data

</details>


</details>

---

<details>
  <summary>Week 2: 5/12/2023~11/12/2023 </summary>
<br>

<details>

<summary>Cao Thành Duy</summary>

- Assigned tasks:
    - Build VnPay payment

- Implementation details:
    - Pull Request(s): 
    - Specific implementation details:
        - Build VnPay connection

</details>

<details>
<summary>Hà Bửu Định</summary>

- Assigned tasks:
    - Build usecase CRUD Media for admin

- Implementation details:
    - Pull Request(s): https://github.com/dinhhb/TKXDPM.VP.20231-02/pull/5
    - Specific implementation details:
        - Build view, controller for usecase CRUD Media

</details>


<details>
<summary>Nguyễn Đình Dương</summary>

- Assigned tasks:
    - Build homescreen but not finished
    - Fix viewCart

- Implementation details:
    - Pull Request(s): 
    - Specific implementation details:

</details>


</details>


---

<details>
  <summary>Week 3: 12/12/2023~18/12/2023 </summary>
<br>

<details>

<summary>Cao Thành Duy</summary>

- Assigned tasks:
    - Fix VnPay payment
    - Build return result payment

- Implementation details:
    - Pull Request(s):
    - Specific implementation details:
        - Fix VnPay connection 
        - Build return result payment


</details>

<details>
<summary>Hà Bửu Định</summary>

- Assigned tasks:
    - Fix usecase CRUD Media for admin

- Implementation details:
    - Pull Request(s):
    - Specific implementation details:
        - Fix view, controller for usecase CRUD Media

</details>


<details>
<summary>Nguyễn Đình Dương</summary>

- Assigned tasks:
    - Display invoice
    - Export invoice

- Implementation details:
    - Pull Request(s): https://github.com/dinhhb/TKXDPM.VP.20231-02/pull/6
    - Specific implementation details:
        - Display successful invoice
        - Export invoice and test QR payment
        - Fix Aims homescreen

</details>

</details>


---

<details>
  <summary>Week 4: 19/12/2023~25/12/2023 </summary>
<br>

<details>

<summary>Cao Thành Duy</summary>

- Assigned tasks:
    - Done Payment VnPay

- Implementation details:
    - Pull Request(s): https://github.com/dinhhb/TKXDPM.VP.20231-02/pull/9
    - Specific implementation details:
        - Change in VnPayController
        - Return Url after payment


</details>

<details>
<summary>Hà Bửu Định</summary>

- Assigned tasks:
    - Refactor Admin CRUD Media: using factory pattern and singleton pattern to avoid control coupling and ease of extension 

- Implementation details:
    - Pull Request(s): https://github.com/dinhhb/TKXDPM.VP.20231-02/pull/8
    - Specific implementation details:
        - Create class MediaScreenFactory to create instances of different types of MediaScreen based on the category of media
        - Write BookScreen, CDScreen, … class to load the fxml of add book detail, add cd detail
        - Move database execution code from adminController to MediaService -> enhance cohesion
        - Using singleton pattern in MediaService to ensure only one instance of MediaService exist
        - Create class MediaServiceFactory to create instances of services IMediaService
        - Write BookService, CDService,… classs to provide specific business logic for handling Book, CD media type

</details>


<details>
<summary>Nguyễn Đình Dương</summary>

- Assigned tasks:
    - Update invoice 
    - Build home page screen

- Implementation details:
    - Pull Request(s): https://github.com/dinhhb/TKXDPM.VP.20231-02/pull/7
    - Specific implementation details:
        - Update and retrieve data from database for invoice
        - Remove redundant files payment
        - Build home page screen

</details>
</details>

---

<details>
  <summary>Week 5: 26/12/2023~01/01/2024 </summary>
<br>

<details>

<summary>Cao Thành Duy</summary>

- Assigned tasks:
    - Update VnPayController 
    - Update diagrams

- Implementation details:
    - Pull Request(s): https://github.com/dinhhb/TKXDPM.VP.20231-02/pull/11
    - Specific implementation details:
        - Update VnPayController to jump directly to payment URL
        - Update sequence diagra, acticity diagram and class diagram


</details>

<details>
<summary>Hà Bửu Định</summary>

- Assigned tasks:
    - Refactor code
    - Revise charts and document requirements

- Implementation details:
    - Pull Request(s): https://github.com/dinhhb/TKXDPM.VP.20231-02/pull/12
    - Specific implementation details:
        - Update view and controller for cart
        - Revise charts and document requirements


</details>


<details>
<summary>Nguyễn Đình Dương</summary>

- Assigned tasks:
    - Update and fix code
    - Update diagrams

- Implementation details:
    - Pull Request(s): https://github.com/dinhhb/TKXDPM.VP.20231-02/pull/10
    - Specific implementation details:
        - Update view and controller for cart 
        - Update view and controller for shipping 
        - Update view and controller for home
        - Update diagrams Data modeling, Usecase

</details>

</details>


