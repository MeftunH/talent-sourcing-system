package com.tss.talentsourcingsystem.application.interaction.entity;

import com.tss.talentsourcingsystem.application.candidate.entity.Candidate;
import com.tss.talentsourcingsystem.application.generic.entity.BaseEntity;
import com.tss.talentsourcingsystem.application.interaction.enums.CandidateResponseType;
import com.tss.talentsourcingsystem.application.interaction.enums.InteractionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */
@Entity
@Getter
@Setter
@Table(name = "interaction")
public class Interaction  extends BaseEntity {
    @Id
    @SequenceGenerator(name = "interactionSeq", sequenceName = "interaction_id_seq")
    @GeneratedValue(generator="interactionSeq")
    private Long id;

    @Column(name = "content", nullable = false, length = 1000)
    private String content;
    @Enumerated(EnumType.STRING)
    @Column(name = "is_candidate_responded", length = 30)
    private CandidateResponseType isCandidateResponded;
    @Enumerated(EnumType.STRING)
    @Column(name = "interaction_type", nullable = false, length = 30)
    private InteractionType interactionType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;
}
