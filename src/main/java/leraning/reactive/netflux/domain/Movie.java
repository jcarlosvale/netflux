package leraning.reactive.netflux.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Movie {

    private String id;

    @NonNull
    private String title;

}
