    window.addEventListener("scroll", function () {
    console.log("Script loaded");

  const navbar = document.getElementById("navbar");
  if (window.scrollY > 50) {
    navbar.classList.add("scrolled");
  } else {
    navbar.classList.remove("scrolled");
  }
});


const texts = [
    "Residential Interior",
    "Commercial Interior",
    "Hospitality Interior",
    "Healthcare Interior",
    "Educational Interior",
    "Industrial Interior",
    "Recreational Interior",
    "Institutional Interior"
  ];

  let index = 0;
  let charIndex = 0;
  const typingElement = document.getElementById("t1");
  const typingSpeed = 400; // typing speed in ms
  const eraseSpeed = 100;  // erasing speed
  const delayBetween = 1200; // delay before erasing

  function type() {
    if (charIndex < texts[index].length) {
      typingElement.textContent += texts[index].charAt(charIndex);
      charIndex++;
      setTimeout(type, typingSpeed);
    } else {
      setTimeout(erase, delayBetween);
    }
  }

  function erase() {
    if (charIndex > 0) {
      typingElement.textContent = texts[index].substring(0, charIndex - 1);
      charIndex--;
      setTimeout(erase, eraseSpeed);
    } else {
      index = (index + 1) % texts.length; // next text
      setTimeout(type, typingSpeed);
    }
  }

  document.addEventListener("DOMContentLoaded", function() {
    type(); // start animation on load
  });


   /*function openMenu() {
     const sidebar = document.getElementById("sidebar");
     sidebar.style.width = "300px";
     sidebar.style.visibility = "visible";
     sidebar.style.opacity = "1";
     document.getElementById("overlay").style.display = "block";
   }*/

    function openMenu() {
      const sidebar = document.getElementById("sidebar");
      const overlay = document.getElementById("overlay");
      console.log("sidebar:", sidebar);
      console.log("overlay:", overlay);
      if (sidebar && overlay) {
        sidebar.classList.add("active");
        overlay.style.display = "block";
      }
    }


   function closeMenu() {
     const sidebar = document.getElementById("sidebar");
     sidebar.style.width = "0";
     sidebar.style.visibility = "hidden";
     sidebar.style.opacity = "0";
     document.getElementById("overlay").style.display = "none";
   }



    function initProjectForm() {
        const form = document.getElementById('projectForm');
        if (!form) return; // prevent error if form not found

        form.addEventListener('submit', async function(e) {
            e.preventDefault();

            const formData = new FormData(form);

            if (!form.name.value.trim() || !form.location.value.trim() ||
                !form.description.value.trim() || !form.area.value.trim()) {
                alert("⚠️ Please fill all fields");
                return;
            }

            try {
                const response = await fetch('http://localhost:8080/projects/add', {
                    method: 'POST',
                    body: formData
                });

                if (response.ok) {
                    alert("✅ Project added successfully!");
                    form.reset();
                } else {
                    const errorMsg = await response.text();
                    alert("❌ Failed to add project: " + errorMsg);
                }
            } catch (error) {
                console.error(error);
                alert("⚠️ Network error");
            }
        });
    }

    // Run when page is loaded
    window.addEventListener("DOMContentLoaded", initProjectForm);




    function initCareerFormValidation() {
      const careerForm = document.getElementById("careerForm");
      const successOverlay = document.getElementById("successOverlay");
      const closeBtn = document.getElementById("closeBtn");

      if (!careerForm) return;

      careerForm.addEventListener("submit", function (e) {
        const age = parseInt(document.getElementById("age").value);
        const whyGajmor = document.querySelector("textarea[name='whyGajmor']").value.toLowerCase();

        // Validation: Age
        if (age < 18) {
          alert("You must be at least 18 years old to apply.");
          e.preventDefault();
          return;
        }

        // Validation: Must mention "Gajmor"
        if (!whyGajmor.replace(/\s+/g, " ").includes("gajmor")) {
          alert("Please mention 'Gajmor' in your answer to the second question.");
          e.preventDefault();
          return;
        }

        // Show overlay
        if (successOverlay) {
          successOverlay.style.display = "flex";
        }

        // Delay submission so animation is visible
        e.preventDefault();
        setTimeout(() => {
          e.target.submit();
        }, 1500);
      });

      // Close overlay
      if (closeBtn && successOverlay) {
        closeBtn.addEventListener("click", () => {
          successOverlay.style.display = "none";
        });
      }
    }


    // ✅ Main function to handle form submission
    function handleProjectForm(formId, url) {
        const form = document.getElementById(formId);

        form.addEventListener('submit', async function (e) {
            e.preventDefault();

            const formData = new FormData(form);

            // Frontend validation
            if (!form.projectName.value.trim() ||
                !form.location.value.trim() ||
                !form.description.value.trim() ||
                !form.area.value.trim()) {
                alert("⚠️ Please fill all fields");
                return;
            }

            try {
                const response = await fetch(url, {
                    method: 'POST',
                    body: formData
                });

                if (response.ok) {
                    showSuccessOverlay("✅ Project added successfully!");
                    form.reset();
                } else {
                    showSuccessOverlay("❌ Failed to add project. Please try again.");
                }
            } catch (error) {
                console.error(error);
                showSuccessOverlay("⚠️ Network error");
            }
        });
    }

    // ✅ Success Overlay Function
    function showSuccessOverlay(message) {
        const overlay = document.getElementById("successOverlay");
        const msg = document.getElementById("overlayMessage");

        msg.textContent = message;
        overlay.style.display = "flex";

        // Auto-close after 3s
        setTimeout(() => {
            overlay.style.display = "none";
        }, 3000);
    }

    // ✅ Close Button Handler
    function initOverlayClose() {
        document.getElementById("closeBtn").addEventListener("click", function () {
            document.getElementById("successOverlay").style.display = "none";
        });
    }

    // ✅ Initialize everything
    document.addEventListener("DOMContentLoaded", function () {
        handleProjectForm("projectForm", "http://localhost:8080/add-Projects");
        initOverlayClose();
    });
