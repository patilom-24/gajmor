package com.gajmor.Gajmore.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gajmor.Gajmore.Model.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
