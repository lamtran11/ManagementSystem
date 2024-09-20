document.addEventListener("DOMContentLoaded", function() {

    fetch("http://localhost:8080/api/departments")
        .then(repsonse => repsonse.json())
        .then(departments => {

            const departmentSelect = document.getElementById("department");

            departments.forEach(department => {
                const option = document.createElement("option");
                option.value = department.departmentId;
                option.textContent = department.departmentName;
                departmentSelect.appendChild(option);
            });
        });

    fetch("http://localhost:8080/api/courses")
        .then(response => response.json())
        .then(courses => {

            const courseContainer = document.getElementById("course-container");

            courses.forEach(course => {
               
                const checkBox = document.createElement("input");
                checkBox.type = "checkbox";
                checkBox.name = "course";
                checkBox.value = course.courseName;
                
                const label = document.createElement("label");
                label.htmlFor = `course-${course.courseName}`;
                label.textContent = `${course.courseName} (${course.department_name}`;

                courseContainer.appendChild(checkBox);
                courseContainer.appendChild(label);
                courseContainer.appendChild(document.createElement("br"));

        })
    });
});