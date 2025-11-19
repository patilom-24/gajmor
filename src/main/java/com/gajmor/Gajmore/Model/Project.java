			package com.gajmor.Gajmore.Model;
			
			import java.util.List;
	
	import com.fasterxml.jackson.annotation.JsonManagedReference;
	
	import jakarta.persistence.CascadeType;
			import jakarta.persistence.Entity;
			import jakarta.persistence.GeneratedValue;
			import jakarta.persistence.GenerationType;
			import jakarta.persistence.Id;
			import jakarta.persistence.OneToMany;
            import lombok.AllArgsConstructor;
            import lombok.Data;
            import lombok.NoArgsConstructor;

            @Entity
            @AllArgsConstructor
            @NoArgsConstructor
            @Data
			public class Project {
			    @Id
			    @GeneratedValue(strategy = GenerationType.IDENTITY)
			    private Long id;
			
			    private String name;
			    private String projectType;
			    private String description;
			    private String location;
			    private Double area;
			
			    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
			    @JsonManagedReference
			    private List<ProjectImage> images;
			

			}