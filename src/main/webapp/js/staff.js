const modalContainer = document.getElementById("modalContainer");
const addEmployeeBtn = document.getElementById("addEmployeeBtn");
const closeModal = document.getElementById("closeModal");
const jobSearch = document.getElementById("jobSearch");
const jobOptions = document.getElementById("jobOptions");
const selectedJobs = document.getElementById("selectedJobs");

const allJobs = ["Стажёр", "Рабочий", "Менеджер", "Директор"];
const selectedJobValues = new Set();

addEmployeeBtn.addEventListener("click", () => {
    modalContainer.style.display = "block";
    jobOptions.innerHTML = "";
});


closeModal.addEventListener("click", () => {
    modalContainer.style.display = "none";
});

function addJob(job) {
    if (!selectedJobValues.has(job)) {
        selectedJobValues.add(job);

        const jobTag = document.createElement("div");
        jobTag.className = "job-tag";
        jobTag.innerHTML = `${job} <span class="remove-tag">&times;</span>`;

        jobTag.querySelector(".remove-tag").addEventListener("click", () => {
            jobTag.remove();
            selectedJobValues.delete(job);
            hideJobOptions();
        });

        selectedJobs.appendChild(jobTag);
        hideJobOptions();
    }
}

function filterJobs() {
    const searchValue = jobSearch.value.toLowerCase();
    jobOptions.innerHTML = "";
    const filteredJobs = allJobs.filter(job =>
        job.toLowerCase().includes(searchValue) && !selectedJobValues.has(job)
    );

    if (filteredJobs.length > 0) {
        jobOptions.style.display = 'block';
        filteredJobs.forEach(job => {
            const optionDiv = document.createElement("div");
            optionDiv.textContent = job;
            optionDiv.className = "job-option";
            optionDiv.onclick = () => addJob(job);
            jobOptions.appendChild(optionDiv);
        });
    } else {
        jobOptions.style.display = 'none';
    }
}

function showJobOptions() {
    if (jobSearch.value) {
        filterJobs();
    }
}

function hideJobOptions() {
    jobOptions.style.display = 'none';
}

window.onclick = (event) => {
    if (event.target === modalContainer) {
        modalContainer.style.display = "none";
    }
    if (event.target !== jobSearch) {
        hideJobOptions();
    }
}

function updatePositionsField() {
    const positionsInput = document.getElementById("positions");
    positionsInput.value = Array.from(selectedJobValues).join(",");
}