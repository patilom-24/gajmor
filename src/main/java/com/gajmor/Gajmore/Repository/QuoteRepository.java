package com.gajmor.Gajmore.Repository;

import com.gajmor.Gajmore.Model.QuoteRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepository extends JpaRepository<QuoteRequest,Long> {
}
