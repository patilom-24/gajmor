window.loadGoogleReviews = function () {

    const container = document.getElementById("reviewsContainer");
    if (!container) {
        console.error("❌ reviewsContainer not found");
        return;
    }

    const service = new google.maps.places.PlacesService(
        document.createElement("div")
    );

    service.getDetails({
        placeId: "ChIJr0rJ1Ti5wjsR4MRoP9v5Nko", // 🔴 paste real Place ID
        fields: ["name", "rating", "reviews"]
    }, (place, status) => {

        console.log("Google Status:", status);
        console.log("Place Data:", place);

        if (status !== google.maps.places.PlacesServiceStatus.OK) {
            container.innerHTML =
                "<p style='color:red'>Failed to load reviews</p>";
            return;
        }

        place.reviews.forEach(r => {
            container.innerHTML += `
                <div class="review-card">
                    <img src="${r.profile_photo_url}" width="40">
                    <strong>${r.author_name}</strong><br>
                    ⭐ ${r.rating}<br>
                    ${r.text}
                </div>
            `;
        });
    });
};
